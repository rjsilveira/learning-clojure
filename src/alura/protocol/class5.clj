(ns alura.protocol.class5
  (:use [clojure pprint])
  (:require [schema.core :as s]))

(defn authorization-type
  "Define authorization type"
  [request]
  (let [patient (:patient request)
        situation (:situation patient)]
    (cond (= :urgent situation) :always-authorized
          (contains? patient :plan) :health-plan
          :else :minimum-credit)))

(defmulti should-assign-pre-authorization? authorization-type)

(defmethod should-assign-pre-authorization?
  :always-authorized
  [_]
  false)

(defmethod should-assign-pre-authorization?
  :health-plan
  [request]
  (not (some #(= % (:procedure request)) (:plan (:patient request)))))

(defmethod should-assign-pre-authorization?
  :minimum-credit
  [request]
  (-> request
      :amount
      (>= 50)))

(let [rogerio {:id        15
               :name      "Rogério"
               :birth     "19/07/2002"
               :situation :normal}
      igor {:id        20
            :name      "Igor"
            :birth     "18/06/2001"
            :situation :normal
            :procedure ["Colonoscopy" "X-Ray"]}]
  (pprint (should-assign-pre-authorization? {:patient rogerio :amount 1000 :procedure "X-Ray"}))
  (pprint (should-assign-pre-authorization? {:patient igor :amount 1000 :procedure "X-Ray"})))