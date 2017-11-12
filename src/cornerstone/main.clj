(ns cornerstone.main
  (:gen-class)
  (:require [clojure.tools.cli :as cli]
            [cornerstone.core]
            [taoensso.timbre :as timbre]
            [mount.core :as mount]))


(def cli-options
  [["-e" "--environment ENVIRONMENT" "The environment to start the server in."
    :id :env
    :default :prod
    :parse-fn keyword
    :validate [#{:prod :dev :stage} "Must be one of #{:prod, :stage, :dev}"]]])


(defn- exit [status msg]
  (System/exit status))


;; TODO: add proper shutdown hook

(defn -main
  "The entrypoint to the application."
  [& args]
  (let [{:keys [options errors]} (cli/parse-opts args cli-options)]
    (when (some? errors)
      (exit 1 (clojure.string/join "\n" errors)))
    (mount/start-with-args {:env (:env options)})))
