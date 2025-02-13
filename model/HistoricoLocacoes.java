package model;

public class HistoricoLocacoes {
    private String id_historico;
    private String id_locacao;
    private String id_funcionario; 
    private String id_cliente;
    private String placa_veiculo;
    private String data_inicio;
    private String data_termino;
    private String forma_pagamento;
    private double valor_pago;

    public HistoricoLocacoes(String id_historico, String id_locacao, String id_funcionario, String id_cliente, String placa_veiculo, String data_inicio, String data_termino, String forma_pagamento, int valor_pago){
        this.id_historico = id_historico;
        this.id_locacao = id_locacao;
        this.id_funcionario = id_funcionario;
        this.id_cliente = id_cliente;
        this.placa_veiculo = placa_veiculo;
        this.data_inicio = data_inicio;
        this.data_termino = data_termino;
        this.forma_pagamento = forma_pagamento;
        this.valor_pago = valor_pago;
        }
    }

