(ns tube.radiator-display)

(defn millis->datestr [millis]
  (.toString (js/Date. millis)))

(defn simple-parameter-contents [parameter]
  (let [value (:value parameter)]
    (println "value " value)
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
