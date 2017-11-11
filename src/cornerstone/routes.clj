(ns cornerstone.routes
  (:require [compojure.core :refer [defroutes GET POST]]
            [ring.util.response :as response]
            [taoensso.timbre :as timbre]
            [mount.core :refer [defstate]]))


(defn root-handler [req]
  (-> (response/file-response "resources/public/index.html")
      (response/content-type "text/html")))


(defroutes routes
  (GET "*" [] root-handler))
