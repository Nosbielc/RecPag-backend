-- noinspection SqlNoDataSourceInspectionForFile
-- create sequence HIBERNATE_SEQUENCE;

 create table conta (
     id bigint auto_increment not null,
     str_nome varchar(255) not null,
     str_chave varchar(255) not null,
     primary key (id)
 );

 create table transferencia (
    id bigint auto_increment not null,
    vlr_transacao DECIMAL(20, 2) not null,
    dt_transacao timestamp not null,
    origem_id bigint not null,
    destino_id bigint not null,
    transacao_status integer not null,
    transacao_tipo integer not null,
    primary key (id)
 );

 alter table transacao add constraint FK_CONTA_ORIGEM foreign key (origem_id) references conta;
 alter table transacao add constraint FK_CONTA_DESTINO foreign key (destino_id) references conta;


