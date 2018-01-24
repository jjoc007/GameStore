CREATE TABLE public.customer_purchase
(
  purchase_id character varying(255) NOT NULL,
  date_of_purchase date,
  other_purchase_details text,
  customer_id character varying(255),
  CONSTRAINT customer_purchase_pkey PRIMARY KEY (purchase_id),
  CONSTRAINT customer_purchase_customer_id_fkey FOREIGN KEY (customer_id)
      REFERENCES public.customer (customer_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.customer_purchase
  OWNER TO postgres;