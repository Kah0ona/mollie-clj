(ns mollie-clj.issuers
  (:require
    [mollie-clj.http :refer [GET POST PUT DELETE]]
    [mollie-clj.core :refer [base-url]]
    [mollie-clj.config :refer [check-config-or-throw api-key]]))

(def issuers-url (str base-url "/issuers"))

(defn get-issuer
  [{:keys [issuer-id]}]
  (GET (str issuers-url "/" issuer-id) {}))

(defn list-issuers
  []
  (GET issuers-url {}))
