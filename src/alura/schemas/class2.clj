(ns alura.schemas.class2
  (:use [clojure pprint])
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

;; (s/defrecord Patient [id :- Long name :- s/Str])
;; (pprint (->Patient "1" "Raphael"))

(def Patient
  "Patient schema example"
  {:id   s/Num
   :name s/Str})

(pprint (s/explain Patient))
(pprint (s/validate Patient {:id 1 :name "Raphael"}))
;; (pprint (s/validate Patient {:id 1 :nome "Raphael"}))
;; (pprint (s/validate Patient {:id 1}))
;; (pprint (s/validate Patient {:id 1 :name "Raphael" :age 17}))

(s/defn function-with-return :- Patient
  "Function with return"
  [id :- s/Num, name :- s/Str]
  {:id   id
   :name name
   :age  17})

;; (pprint (function-with-return 1 "Raphael"))

(defn positive?
  "Is positive?"
  [arg]
  (> arg 0))

(pprint (positive? 1))

(def Positive (s/pred positive? 'positive))
(pprint (s/validate Positive 1))
;; (pprint (s/validate Positive -1))

(def Patient
  "Patient schema"
  {:id   (s/constrained s/Int pos?),
   :name s/Str})

(pprint (s/validate Patient {:id 1 :name "Raphael"}))
(pprint (s/validate Patient {:id 1 :name "Raphael"}))