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

(defn app-routes [state]
  (compojure.core/routes
    (GET "/api/hello" request (api/hello state))))

(defn app [state]
  (-> state (app-routes)))

(defn start-server [port]
  (println (str "Starting Tube on port " port))
  (let [state (atom {:config {}})]
    (run-server (site (handler/site (app state))) {:port port})))

(defn -main
  "Starts the server"
  [port]
  (start-server (Integer. port)))
