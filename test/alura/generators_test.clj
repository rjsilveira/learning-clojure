(ns alura.generators-test
  (:require [clojure.test :refer :all]
            [alura.generators.logic :refer :all]
            [alura.generators.model :as a.t.model]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check.properties :as prop]
            [schema.core :as s]))

(s/set-fn-validation! true)

(println (gen/sample gen/boolean 3))
(println (gen/sample gen/small-integer 100))
(println (gen/sample gen/nat 100))
(println (gen/sample gen/string-alphanumeric 100))

(println (gen/sample (gen/vector gen/small-integer 30) 5))

;; (deftest can-join-in-the-queue?-test
;;   (testing "Test"
;;     (doseq [queue (gen/sample (gen/vector gen/string-alphanumeric 0 4))]
;;       (is (can-join-in-the-queue? {:waiting-room queue} :waiting-room)))))
;;
;; (deftest coming-in-test
;;   (testing "Test"
;;     (let [patient (gen/sample gen/string-alphanumeric)]
;;       (doseq [queue (gen/sample (gen/vector gen/string-alphanumeric 0 4))]
;;         (println queue patient)))))

;; (defspec sample-test 120
;;          (prop/for-all [queue (gen/vector gen/string-alphanumeric 0 4)
;;                         patient gen/string-alphanumeric]
;;                        (= {:waiting-room (conj queue patient)}
;;                           (coming-in {:waiting-room queue} :waiting-room patient))))

(def random-name (gen/fmap clojure.string/join (gen/vector gen/char-alphanumeric 10 15)))

(defn count-patients
  "Count patients"
  [hospital]
  (reduce + (map count (vals hospital))))

(defspec granted 10
         (prop/for-all [waiting-room (gen/vector random-name 0 4)
                        x-ray (gen/vector random-name 0 4)
                        ultrasound (gen/vector random-name 0 4)
                        to (gen/elements [:x-ray :ultrasound])]
                       (let [hospital {:waiting-room waiting-room :x-ray x-ray :ultrasound ultrasound}
                             final-hospital (transfer hospital :waiting-room to)]
                         (= (count-patients hospital) (count-patients final-hospital)))))

(defn sum-funciton
  "Sum fucntion"
  [& args]
  (reduce + args))

(let [array [1 2 3 4 5]]
  (println (apply sum-funciton array)))

(println (read-line))