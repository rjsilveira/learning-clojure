(ns alura.logic-test
  (:require [clojure.test :refer :all]
            [alura.test.logic :refer :all]
            [alura.test.model :as a.t.model])
  (:import (clojure.lang ExceptionInfo)))

(deftest can-join-in-the-queue-test?
  (testing "The patient can join in the empty queue?"
    (is (can-join-in-the-queue? {:waiting-room []} :waiting-room)))
  (testing "The patient can join in the fully queue?"
    (is (not (can-join-in-the-queue? {:waiting-room [1 2 3 4 5]} :waiting-room))))
  (testing "The patient can join in the more than fully queue?"
    (is (not (can-join-in-the-queue? {:waiting-room [1 2 3 4 5 6]} :waiting-room))))
  (testing "The patient can join in the queue with nil department?"
    (is (not (can-join-in-the-queue? {:waiting-room [1 2 3 4]} :x-ray)))))

(deftest can-join-in-the-queue-test-2?
  (let [my-hospital {:waiting-room [113 32 21 44 59]}]
    (testing "Patients can join while the queue is not fully"
      (is (= {:waiting-room [1 2 3 4 5]}
             (coming-in {:waiting-room [1 2 3 4]} :waiting-room 5))))
    (testing "Patients can join while the queue is not fully"
      (is (try
            (coming-in my-hospital :waiting-room 65)
            false
            (catch ExceptionInfo exception
              (= :queue-is-full (:error (ex-data exception)))))))))

(deftest transfer-test
  (testing "Success transfer patient"
    (let [hospital {:waiting-room [11] :x-ray []}]
      (is (= {:waiting-room [] :x-ray [11]}
             (transfer hospital :waiting-room :x-ray))))
    (let [hospital {:waiting-room (conj a.t.model/empty-queue 33 44) :x-ray (conj a.t.model/empty-queue 22)}]
      (is (= {:waiting-room [44] :x-ray [22 33]}
             (transfer hospital :waiting-room :x-ray)))))
  (testing "Error transfer patient - Full queue"
    (let [hospital {:waiting-room [66] :x-ray [11 22 33 44 55]}]
      (is (try
            (transfer hospital :waiting-room :x-ray)
            false
            (catch ExceptionInfo exception
              (= :queue-is-full (:error (ex-data exception)))))))))