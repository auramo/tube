(ns tube.core
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [tube.restapi :as api])
  (:use [compojure.route :only [files not-found]]
        [compojure.handler :only [site]]
        [compojure.core :only [defroutes GET POST DELETE ANY context]]
        org.httpkit.server)
  (:gen-class))

(defroutes ring-app
  (GET "/" request "OK")

  ;; API methods
  (GET "/api/hello" request (api/hello))

  ;;  (files "/")
  (route/files "/" {:root "front/web"})
  (compojure.route/not-found "404 not found"))

(def app
  (handler/site ring-app))

(defn start-server [port]
  (println (str "Starting Tube on port " port))
  (run-server (site #'app) {:port port}))

(defn -main
  "Starts the server"
  [port]
  (start-server (Integer. port)))
