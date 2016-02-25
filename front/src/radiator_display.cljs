(ns tube.radiator-display
  (:require [goog.i18n.DateTimeFormat :as dtf]
            [cljs-time.coerce :as time-coerce]
            [cljs-time.format :as time-format]))

(def tstamp-formatter (time-format/formatter "dd.MM.yyyy HH:mm:ss"))

(defn tune-timezone [millis]
  (- millis (* 1000 (* 60 (.getTimezoneOffset (js/Date.))))))

(defn millis->datestr [millis]
  (println (time-coerce/from-long millis))
  (time-format/unparse tstamp-formatter (time-coerce/from-long (tune-timezone millis))))

(defn simple-parameter-contents [parameter]
  (let [value (:value parameter)]
    (if value
      [[:div.warning (str "Updated: " (millis->datestr (:updated parameter)))]
       [:div.parameter (:value parameter)]
       [:div.parameter-name (:title parameter)]]
      [[:div.parameter "N/A"]])))

(defn unknown-type [] [:div.parameter-box.gloss [:div.parameter "??"]])

(defn simple-parameter [parameter]
  (into [:div.parameter-box.gloss] (simple-parameter-contents parameter)))

(defn unknown-type [] [:div.parameter-box.gloss [:div.parameter "??"]])

(def param-mappers {"simple" simple-parameter})

(defn map-parameters [parameters]
  (if parameters
    (map (fn [param]
           (if-let [mapper (get param-mappers (:type param))]
             (mapper param)
             (unknown-type)))
           parameters)
    [[:div.warning "No parameters available"]]))

(defn radiator [state]
  [:div
   [:div.header (str (:title @state) " radiator")]
   (into [:div.parameters] (map-parameters (:parameters @state)))])
