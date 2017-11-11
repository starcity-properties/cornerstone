(ns cornerstone.server
  (:require [clojure.string :as string]
            [cornerstone.routes :as routes]
            [mount.core :refer [defstate]]
            [org.httpkit.server :as http-server]
            [ring.util.response :as response]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [ring.middleware.format :refer [wrap-restful-format]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [ring.middleware.nested-params :refer [wrap-nested-params]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.resource :refer [wrap-resource]]
            [taoensso.timbre :as timbre]))



(defn wrap-exception-handling
  [handler]
  (fn [{:keys [session uri request-method remote-addr params] :as req}]
    (try
      (handler req)
      (catch Exception e
        (do
          (timbre/error e "|UNHANDLED ERROR|" {:uri         uri
                                               :method      request-method
                                               :params      params
                                               :remote-addr remote-addr})
          {:status 500 :body "Unexpected server error!"})))))


(defn wrap-logging
  "Middleware to log requests."
  [handler]
  (fn [{:keys [uri request-method session remote-addr params] :as req}]
    (when-not (or (= uri "/favicon.ico") (string/starts-with? uri "/assets"))
      (timbre/trace "|WEB REQUEST|" {:uri         uri
                                     :params params
                                     :method      request-method
                                     :remote-addr remote-addr}))
    (handler req)))


(defn- handler []
  (-> routes/routes
      (wrap-logging)
      (wrap-keyword-params)
      (wrap-nested-params)
      (wrap-restful-format)
      (wrap-params)
      (wrap-multipart-params)
      (wrap-resource "public")
      (wrap-exception-handling)
      (wrap-content-type)))


(defn- start-server [port]
  (timbre/infof "|STARTING WEB SERVER| port: %d" port)
  (http-server/run-server (handler) {:port port}))


(defn- stop-server [server]
  (timbre/info "|STOPPING WEB SERVER|")
  (server))


(defstate web-server
  :start (start-server 8080)
  :stop (stop-server web-server))
