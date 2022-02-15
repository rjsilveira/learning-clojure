(ns alura.protocol.model
  (:import (java.util Date Calendar)))

(defprotocol Datable
  (to-ms [this]))

(extend-type Number
  Datable
  (to-ms [this] this))

(extend-type Date
  Datable
  (to-ms [this] (.getTime this)))

(extend-type Calendar
  Datable
  (to-ms [this] (to-ms (.getTime this))))
