(ns alura.protocol.class2
  (:use [clojure pprint])
  (:import (java.util Date Calendar GregorianCalendar)))

(defrecord Patient [id name birth])
(defrecord PatientWithHealthPlan [id name birth plan])

;;
;; (defn should-have-pre-authorization?
;;   "Should have pre-authorization"
;;   [patient procedure amount]
;;   (if (= Patient (type patient))
;;     (>= amount 50)
;;     (if (= PatientWithHealthPlan (type patient))
;;       (let [plan (get patient :plan)]
;;         (not (some #(= % procedure) plan)))
;;       true)))

(defprotocol MustCharge
  (should-have-pre-authorization? [patient procedure amount]))

(extend-type Patient
  MustCharge
  (should-have-pre-authorization?
    [_ _ amount]
    (>= amount 50)))

(extend-type PatientWithHealthPlan
  MustCharge
  (should-have-pre-authorization?
    [patient procedure _]
    (let [plan (get patient :plan)]
      (not (some #(= % procedure) plan)))))

(let [rogerio (->Patient 15 "Rogério" "19/07/2002")
      igor (->PatientWithHealthPlan 20 "Igor" "18/06/2001" ["Colonoscopy" "X-Ray"])]
  (pprint (should-have-pre-authorization? rogerio nil 49.9))
  (pprint (should-have-pre-authorization? igor "Unknown exam" nil)))

(defprotocol Datable
  (to-ms [this]))

(extend-type Number
  Datable
  (to-ms [this] this))

(pprint (to-ms 56))

(extend-type Date
  Datable
  (to-ms [this] (.getTime this)))

(pprint (to-ms (Date.)))

(extend-type Calendar
  Datable
  (to-ms [this] (to-ms (.getTime this))))

(pprint (to-ms (GregorianCalendar.)))