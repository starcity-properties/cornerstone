(ns cornerstone.routes
  (:require [compojure.core :refer [defroutes GET POST]]
            [ring.util.response :as response]
            [taoensso.timbre :as timbre]
            [mount.core :refer [defstate]]))


(defn root-handler [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "<h1>cornerstone</h1>"})


(defroutes routes
  (GET "/index" []
       (fn [req]
         (-> (response/resource-response "public/index.html")
             (response/content-type "text/html"))))

  (GET "*" [] root-handler))
