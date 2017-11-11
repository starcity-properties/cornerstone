{:dev
 {:source-paths ["src" "env/dev"]

  :jvm-opts ["-Djava.awt.headless=true"]

  :dependencies [[com.cemerick/piggieback "0.2.1"]
                 [org.clojure/tools.namespace "0.2.11"]]}

 :uberjar
 {:source-paths ["src"]
  :uberjar-name "cornerstone-app.jar"
  :aot          :all
  :main         cornerstone.main}}
