CREATE OR REPLACE PACKAGE                PCK_ACT_MOVIMIENTO AS
  PROCEDURE sp_act_saldo_movimiento  (
                        an_id in number, an_cuentaid in NUMBER, an_valor in decimal, ac_signo in varchar2  )
                         ;
                         
                        
END PCK_act_movimiento; 

/