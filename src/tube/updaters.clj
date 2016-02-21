(ns tube.updaters)

(defn start [configured-updaters state]
  (println (str "Starting updaters " (keys configured-updaters))))
