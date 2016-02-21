(ns tube.core
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [tube.restapi :as api]
            [tube.options :as opts]
            [tube.config :as config])
  (:use [compojure.route :only [files not-found]]
        [compojure.handler :only [site]]
        [compojure.core :only [defroutes GET POST DELETE ANY context]]
        org.httpkit.server
        clojopts.core)
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

(defn start-server []
  (let [state (atom {:config {}})
        opts (opts/get-options)]
    (println (str "Starting Tube on port " (:server-port opts)))
    (run-server (site (handler/site (app state))) {:port (:server-port opts)})))

(defn -main
  "Starts the server"
  [& args]
  (start-server))
