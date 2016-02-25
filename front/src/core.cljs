(ns tube.core
  (:require [reagent.core :as reagent]
            [ajax.core :refer [GET]]
            [tube.radiator-display :refer [radiator]]))

(enable-console-print!)

(def schedule-interval-millis 1000)

(defn handle-response [state response]
  (println "response: " response)
  (reset! state (clojure.walk/keywordize-keys response)))

(defn fetch-state [state post-fetch-fn]
  (GET "/api/radiator-state" {:handler #(do
                                          (handle-response state %)
                                          (post-fetch-fn state))
                                :error-handler #(println "error" %)}))

(defn schedule-update [state]
  (js/setTimeout
   #(fetch-state state (fn [state] (schedule-update state)))
   schedule-interval-millis))

(defn app-fn [state]
  [radiator state])

(defn app-m [state] (with-meta #(app-fn state) {:component-did-mount #(schedule-update state) }))

(defn init []
  (let [state (reagent/atom {:title "Tube"})]
    (reagent/render [(app-m state)] (.getElementById js/document "content"))))

(init)
