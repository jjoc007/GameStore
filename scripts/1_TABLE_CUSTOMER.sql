CREATE TABLE public.customer
(
  customer_id character varying(255) NOT NULL,
  customer_code character varying(100),
  customer_name character varying(100),
  customer_address character varying(200),
  customer_other_details text,
  CONSTRAINT customer_pkey PRIMARY KEY (customer_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.customer
  OWNER TO postgres;