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

(defn simple-parameter [parameter]
  (into [:div.parameter-box.gloss] (simple-parameter-contents parameter)))
          ;; [:div.warning (str "Updated: " (millis->datestr (:updated parameter)))]
          ;; [:div.parameter (:value parameter)]
          ;; [:div.parameter-name (:title parameter)]]))

(defn radiator [state]
  [:div
   [:div.header (str (:title @state) " radiator")]
   [:div.parameters
    (simple-parameter {:id "orders" :title "Orders" :type "simple" :value 44 :updated 1456339348361})
    ;;[:div.parameter-box.gloss
    ;; [:div.warning "Delayed"]
    ;; [:div.parameter 77]
    ;; [:div.parameter-name "Tilaukset"]]
    ]])
