CREATE TABLE Veiculos (
    placa VARCHAR(10) PRIMARY KEY,
    fabricante VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    cor VARCHAR(20) NOT NULL,
    numero_matricula INT NOT NULL UNIQUE,
    tarifa DECIMAL(10,2) NOT NULL
);

CREATE TABLE Funcionarios (
    numero_matricula SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE Clientes (
    id_cliente SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    numero_cnh VARCHAR(20) UNIQUE NOT NULL,
    idade INT NOT NULL,
    sexo CHAR(1) NOT NULL CHECK (sexo IN ('M', 'F'))
);

CREATE TABLE Locacoes (
    id_locacao SERIAL PRIMARY KEY,
    id_funcionario INT NOT NULL,
    id_cliente INT NOT NULL,
    placa_veiculo VARCHAR(10) NOT NULL,
    data_inicio TIMESTAMP NOT NULL,
    data_termino TIMESTAMP NOT NULL,
    forma_pagamento VARCHAR(20) CHECK (forma_pagamento IN ('Dinheiro', 'Cartao de Credito', 'Cartao de Debito', 'PIX')),
    valor_pago DECIMAL(10,2),
    status VARCHAR(20) NOT NULL CHECK (status IN ('Disponivel','Indisponivel')),
    FOREIGN KEY (id_funcionario) REFERENCES Funcionarios(numero_matricula),
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente),
    FOREIGN KEY (placa_veiculo) REFERENCES Veiculos(placa)
);

CREATE TABLE Historico_Locacoes (
    id_historico SERIAL PRIMARY KEY,
    id_locacao INT NOT NULL,
    id_funcionario INT NOT NULL,
    id_cliente INT NOT NULL,
    placa_veiculo VARCHAR(10) NOT NULL,
    data_inicio TIMESTAMP NOT NULL,
    data_termino TIMESTAMP NOT NULL,
    forma_pagamento VARCHAR(20),
    valor_pago DECIMAL(10,2),
    FOREIGN KEY (id_locacao) REFERENCES Locacoes(id_locacao),
    FOREIGN KEY (id_funcionario) REFERENCES Funcionarios(numero_funcionario),
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente),
    FOREIGN KEY (placa_veiculo) REFERENCES Veiculos(placa)
);
