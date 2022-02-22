(ns alura.generators.model
  (:require [schema.core :as s])
  (:import (clojure.lang PersistentQueue)))

(def empty-queue PersistentQueue/EMPTY)

(defn create-hospital
  "Create a new hospital"
  []
  {:waiting-room empty-queue
   :lab-01       empty-queue
   :lab-02       empty-queue
   :lab-03       empty-queue})

(s/def IdPatient s/Str)
(s/def Department [IdPatient])
(s/def Hospital {s/Keyword Department})