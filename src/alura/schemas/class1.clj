(ns alura.schemas.class1
  (:use [clojure pprint])
  (:require [schema.core :as s]))

(defn add-patient
  "Insert a new patient in a list"
  [patients patient]
  (if-let [id (:id patient)]
    (assoc patients id patient)
    (throw (ex-info "Patient must have a id" patient))))

(defn add-visit
  "Insert a new visit in a list"
  [visits patient dates]
  (if (contains? visits patient)
    (update visits patient concat dates)
    (assoc visits patient dates)))

(defn print-patient-visits
  "Print patient visits"
  [visits patient]
  (println "Patient" patient "visits" (get visits (:id patient))))

(defn test-add-patient
  "Test add-patient function"
  []
  (let [rogerio {:id 1 :name "Rogério"}
        igor {:id 2 :name "Igor"}
        gabriel {:id 3 :name "Gabriel"}
        patients (reduce add-patient {} [rogerio igor gabriel])
        visits {}
        visits (add-visit visits 1 ["01/01/2022"])
        visits (add-visit visits 2 ["02/01/2022", "01/01/2022"])
        visits (add-visit visits 1 ["03/01/2022"])]
    (pprint patients)
    (pprint visits)
    (print-patient-visits visits rogerio)))

(test-add-patient)

(pprint (s/validate Long 15))

(s/set-fn-validation! true)

(s/defn simple-test
  "Simple test with defn of prismatic"
  [arg :- Long]
  (pprint arg))

(simple-test 30)
;; error -> (simple-test "Raphael")'

(s/defn create-patient
  "Create a new patient"
  [id :- Long name :- s/Str]
  {:id id, :name name})

(pprint (create-patient 1 "Raphael"))

