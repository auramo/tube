(ns tube.core
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [tube.restapi :as api]
            [tube.options :as opts]
            [tube.config :as config])
  (:use [compojure.route :only [files not-found]]
        [compojure.handler :only [site]]
        [compojure.core :only [defroutes POST context]]
        org.httpkit.server
        clojopts.core)
  (:gen-class))

(defn app-routes [state]
  (compojure.core/routes
   (POST "/api/parameter-update"
         request
         (api/parameter-update state
                               (slurp (request :body))))))

(defn app [state]
  (-> state (app-routes)))

(defn create-initial-state [conf]
  (atom {:title (:title conf)
         :parameters (:parameters conf)}))

(defn start-server []
  (let [opts (opts/get-options)
        conf (config/read-config (:config-file opts))
        state (create-initial-state conf)]
    (println (str "Starting Tube on port " (:server-port opts)))
    (run-server (site (handler/site (app state))) {:port (:server-port opts)})))

(defn -main
  "Starts the server"
  [& args]
  (start-server))
