(defproject cornerstone "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-RC1"]
                 [org.clojure/tools.nrepl "0.2.13"]
                 [org.clojure/tools.cli "0.3.5"]
                 ;; web
                 [http-kit "2.2.0"]
                 [compojure "1.6.0"]
                 [ring/ring "1.6.3"]
                 [ring-middleware-format "0.7.2"]
                 ;; utility
                 [mount "0.1.11"]
                 [aero "1.1.2"]
                 [com.taoensso/timbre "4.10.0"]]

  :repl-options {:init-ns user}

  :main cornerstone.main)
