-- =======================================================================
-- 1. TABELAS INDEPENDENTES (Não possuem chaves estrangeiras)
-- =======================================================================

-- Tabela: funcionario
CREATE TABLE funcionario (
	id bigserial NOT NULL,
	nome varchar(80) NOT NULL,
	cpf varchar(11) NOT NULL,
	funcao varchar(255) NULL,
	login varchar(50) NOT NULL,
	senha varchar(255) NOT NULL,
	CONSTRAINT funcionario_pkey PRIMARY KEY (id),
	CONSTRAINT funcionario_cpf_key UNIQUE (cpf),
	CONSTRAINT funcionario_login_key UNIQUE (login)
);

-- Tabela: paciente
CREATE TABLE paciente (
	id bigserial NOT NULL,
	nome varchar(80) NOT NULL,
	cpf varchar(12) NOT NULL,
	endereco varchar(80) NOT NULL,
	telefone varchar(20) NOT NULL,
	genero varchar(255) NULL,
	data_nascimento date NOT NULL,
	email varchar(255) NULL,
	CONSTRAINT paciente_pkey PRIMARY KEY (id)
);

-- =======================================================================
-- 2. TABELAS DEPENDENTES (Possuem vínculos e relacionamentos)
-- =======================================================================

-- Tabela: atendimento
CREATE TABLE atendimento (
	id bigserial NOT NULL,
	data_atendimento date NULL,
	hora_chegada time(6) NULL,
	hora_inicio time(6) NULL,
	hora_fim time(6) NULL,
	senha_triagem varchar(255) NULL,
	senha_atendimento varchar(255) NULL,
	prioridade int2 NULL,
	status varchar(255) NULL,
	especialidade varchar(255) NULL,
	observacoes varchar(255) NULL,
	tipo varchar(255) NULL,
	paciente_id int8 NULL,
	CONSTRAINT atendimento_pkey PRIMARY KEY (id)
);

-- Tabela: triagem
CREATE TABLE triagem (
	id bigserial NOT NULL,
	atendimento_id int8 NULL,
	pressao float8 NULL,
	temperatura float8 NULL,
	glicemia float8 NULL,
	queixa varchar(255) NULL,
	historico_rapido varchar(255) NULL,
	escala_dor int4 NULL,
	observacoes varchar(255) NULL,
	funcionario_id int8 NULL,
	hora time(6) NULL,
	CONSTRAINT triagem_pkey PRIMARY KEY (id)
);

-- =======================================================================
-- 3. CHAVES ESTRANGEIRAS (Constraints de relacionamento)
-- =======================================================================

-- Vínculos da tabela atendimento
ALTER TABLE atendimento 
    ADD CONSTRAINT fk_atendimento_paciente 
    FOREIGN KEY (paciente_id) REFERENCES paciente(id);

-- Vínculos da tabela triagem
ALTER TABLE triagem 
    ADD CONSTRAINT fk_triagem_atendimento 
    FOREIGN KEY (atendimento_id) REFERENCES atendimento(id);

ALTER TABLE triagem 
    ADD CONSTRAINT fk_triagem_funcionario 
    FOREIGN KEY (funcionario_id) REFERENCES funcionario(id);