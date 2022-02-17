(ns alura.schemas.class3
  (:use [clojure pprint])
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

(def PosInt (s/pred pos-int?))

(def FinancialAmount (s/constrained s/Num (fn [arg] (>= arg 0))))

(def Plan [s/Keyword])

(def Patient
  {:id                     PosInt
   :name                   s/Str
   :plan                   Plan
   (s/optional-key :birth) s/Str})

(def Request
  {:patient   Patient
   :amount    FinancialAmount
   :procedure s/Keyword})

(s/defn patient-factory :- Patient
  "Patient factory"
  [id :- PosInt
   name :- s/Str
   plan :- Plan]
  {:id   id
   :name name
   :plan plan})

(s/defn request-factory :- Request
  "Request factory"
  [patient :- Patient
   amount :- FinancialAmount
   procedure :- s/Keyword]
  {:patient   patient
   :amount    amount
   :procedure procedure})

(pprint (patient-factory 1 "Raphael" [:x-ray :colonoscopy]))
(pprint (patient-factory 2 "Rogério" [:x-ray :colonoscopy]))
(pprint (request-factory (patient-factory 1 "Raphael" [:x-ray :colonoscopy]) 10.0 :x-ray))

(def Patients {PosInt Patient})

(pprint (s/validate Patients {15 (patient-factory 1 "Raphael" [:x-ray :colonoscopy])}))

(def Visits {PosInt [s/Str]})

