(ns cornerstone.routes
  (:require [compojure.core :refer [defroutes GET POST]]
            [ring.util.response :as response]
            [taoensso.timbre :as timbre]
            [mount.core :refer [defstate]]))


(defn root-handler [req]
  #_(-> (response/file-response "resources/public/index.html")
      (response/content-type "text/html"))
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "<h1>cornerstone</h1>"})


(defroutes routes
  (GET "*" [] root-handler))
