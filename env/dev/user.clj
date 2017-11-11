(ns user
  (:require [clojure.tools.namespace.repl :refer [refresh]]
            [cornerstone.core]
            [mount.core :as mount]
            [taoensso.timbre :as timbre]))


(defn start []
  (timbre/debug "starting system...")
  (mount/start)
  (timbre/debug "all systems go!"))


(defn stop []
  (timbre/debug "stopping system...")
  (mount/stop)
  (timbre/debug "all systems stopped."))


(defn go []
  (start)
  :ready)


(defn reset []
  (stop)
  (refresh :after 'user/go))
