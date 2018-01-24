CREATE TABLE public.customer_order
(
  order_id character varying(255) NOT NULL,
  date_of_order date,
  other_order_details text,
  customer_id character varying(255),
  CONSTRAINT customer_order_pkey PRIMARY KEY (order_id),
  CONSTRAINT customer_order_customer_id_fkey FOREIGN KEY (customer_id)
      REFERENCES public.customer (customer_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.customer_order
  OWNER TO postgres;