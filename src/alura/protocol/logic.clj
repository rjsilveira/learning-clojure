(ns alura.protocol.logic
  (:require [alura.protocol.model :as a.p.model])
  (:import (java.util Date)))

(defn now
  "Return the time of now in ms"
  []
  (a.p.model/to-ms (Date.)))
