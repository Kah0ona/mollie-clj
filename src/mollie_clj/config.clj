(ns mollie-clj.config)

(def api-key (atom nil))

(defn check-config-or-throw
  []
  (assert @api-key "The API key is not set, please use mollie-clj.core/set-api-key! to set one."))
