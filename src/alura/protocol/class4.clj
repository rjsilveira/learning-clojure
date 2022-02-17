(ns alura.protocol.class4
  (:use [clojure pprint]))

(defrecord Patient [id name birth situation])
(defrecord PatientWithHealthPlan [id name birth situation plan])

(defprotocol MustCharge
  (should-have-pre-authorization? [patient procedure amount]))

(defn is-urgent?
  "Is the patient situation urgent?"
  [patient]
  (= :urgent (:situation patient :normal)))

(extend-type Patient
  MustCharge
  (should-have-pre-authorization?
    [patient _ amount]
    (and
      (>= amount 50)
      (not (is-urgent? patient)))))

(extend-type PatientWithHealthPlan
  MustCharge
  (should-have-pre-authorization?
    [patient procedure _]
    (let [plan (get patient :plan)]
      (and
        (not (some #(= % procedure) plan))
        (not (is-urgent? patient))))))

(let [rogerio (->Patient 15 "Rogério" "19/07/2002" :normal)
      igor (->PatientWithHealthPlan 20 "Igor" "18/06/2001" :normal ["Colonoscopy" "X-Ray"])]
  (pprint (should-have-pre-authorization? rogerio nil 60.9))
  (pprint (should-have-pre-authorization? igor "Unknown exam" nil)))

(defmulti rewrite-should-have-pre-authorization? class)
(defmethod rewrite-should-have-pre-authorization? Patient [patient]
  (println "Invocation for Patient")
  true)
(defmethod rewrite-should-have-pre-authorization? PatientWithHealthPlan [patient]
  (println "Invocation for PatientWithHealthPlan")
  false)

(let [rogerio (->Patient 15 "Rogério" "19/07/2002" :normal)
      igor (->PatientWithHealthPlan 20 "Igor" "18/06/2001" :normal ["Colonoscopy" "X-Ray"])]
  (pprint (rewrite-should-have-pre-authorization? rogerio))
  (pprint (rewrite-should-have-pre-authorization? igor)))

(defn my-verification-function
  "My verification function"
  [clazz]
  (println clazz)
  (class clazz))

(defmulti rewrite-another-should-have-pre-authorization? my-verification-function)
;; (rewrite-another-should-have-pre-authorization? "Alou")

(defn authorization-type
  "Define authorization type"
  [request]
  (let [patient (:patient request)
        situation (:situation patient)
        is-urgent? (= :urgent situation)]
    (if is-urgent?
      :always-authorized
      (class patient))))

(defmulti should-request-pre-authorization? authorization-type)

(defmethod should-request-pre-authorization?
  :always-authorized
  [_]
  false)

(defmethod should-request-pre-authorization?
  PatientWithHealthPlan
  [request]
  (not (some #(= % (:procedure request)) (:plan (:patient request)))))

(println "-----------------------------------------------")

(let [rogerio (->Patient 15 "Rogério" "19/07/2002" :urgent)
      igor (->PatientWithHealthPlan 20 "Igor" "18/06/2001" :urgent ["Colonoscopy" "X-Ray"])]
  (pprint (should-request-pre-authorization? {:patient rogerio :amount 1000 :procedure "X-Ray"}))
  (pprint (should-request-pre-authorization? {:patient igor :amount 1000 :procedure "X-Ray"})))