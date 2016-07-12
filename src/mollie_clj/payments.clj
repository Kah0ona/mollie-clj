(ns mollie-clj.payments
  (:require 
    [mollie-clj.http :refer [GET POST PUT DELETE]] 
    [mollie-clj.core :refer [base-url]]
    [mollie-clj.config :refer [check-config-or-throw api-key]]))

(def payments-url (str base-url "/payments"))

(defn get-payment
  [{:keys [payment-id]}]
  (GET (str payments-url "/" payment-id) {}))

(defn create-payment
  [params]
  (POST payments-url params))

(defn list-payments
  []
  (GET payments-url {}))

(defn create-payment-refund
  [{:keys [id amount] :as params}]
  (POST 
    (str payments-url "/" id "/refunds")
    params))

(defn get-payment-refund
  [{:keys [payment-id refund-id] :as params}]
  (GET (str payments-url "/" payment-id "/refunds/" refund-id)
       {}))

(defn cancel-payment-refund
  [{:keys [payment-id refund-id] :as params}]
  (DELETE (str payments-url "/" payment-id "/refunds/" refund-id) {}))
  
(defn list-payment-refunds
  [{:keys [payment-id]}]
  (GET (str payments-url "/" payment-id "/refunds")
       {}))



(comment

  (mollie-clj.core/set-api-key! "test_jBMAvfTRBRGKN7hmcSGF72P4sRABjK")
  (create-payment
    {"amount" 100.00
     "description" "EDUTEQ " 
     "redirectUrl" "http://localhost/student-portal"
     "webhookUrl"  "http://localhost/student-portal"
     "method" "ideal"
     "issuer" "ideal_TESTNL99"})

  )
