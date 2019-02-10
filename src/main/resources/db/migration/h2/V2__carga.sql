-- noinspection SqlNoDataSourceInspectionForFile

INSERT INTO conta ( str_nome, str_chave, str_chave_vendedor, str_chave_comprador, str_telefone)
VALUES ('00000000000', '00000000000', '00000000000000000000000000000000', '00000000000000000000000000000000', '5581987781470' ),
       ('96728947248', '96728947248', 'ea3d93fff2744eb7927939e83866ddbe', 'a9d8019d97f642b7a8ca0e72e5a34b4c', '5581982182560' ),
       ('51761046446', '51761046446', '8b9d0c6f15b04b11b59d49c178fe8f64', '948fa78e17cd4c1694f3c5e83ce21231', '5581996005541' );

INSERT INTO transacao ( vlr_transacao,
                        str_descricao,
                        str_autenticador,
                        dt_transacao,
                        origem_id,
                        destino_id,
                        transacao_status,
                        transacao_tipo ) VALUES ( 10000.00, 'CREDITO_INVESTIDOR','1Q2W3E4R4E3W2Q12W3E',
                                                  SYSDATE(), 1, 2, 3, 0) ,
                                                ( 10000.00, 'CREDITO_INVESTIDOR','QRTQERT3T34GQRGQERGQ',
                                                  SYSDATE(), 1, 3, 3, 0);