CREATE OR REPLACE PACKAGE BODY                PCK_ACT_MOVIMIENTO AS

    PROCEDURE sp_act_saldo_movimiento(an_id in number, an_cuentaid in NUMBER, an_valor in decimal, ac_signo in varchar2  )
    AS
    PRAGMA AUTONOMOUS_TRANSACTION;
                 
    ln_saldo_ult NUMBER;
    ln_id_max  NUMBER;

    BEGIN

        ln_id_max  := 0;
        ln_saldo_ult := 0;
                        

        IF an_id > 0  and an_valor > 0 and ( ac_signo ='+' or ac_signo = '-' ) THEN 

            ln_id_max  := 0;
            ln_saldo_ult := 0;

            select max(id)
            INTO ln_id_max  
            from administracion.movimientos m
            where m.cuenta_id = an_cuentaid
            and m.id not in ( an_id ) ;

            if ln_id_max  > 0 then

                select saldo
                INTO ln_saldo_ult 
                from administracion.movimientos m
                where m.id = ln_id_max;
                                    
                if ac_signo = '+' then
                    ln_saldo_ult := ln_saldo_ult + an_valor;
                else
                    ln_saldo_ult := ln_saldo_ult - an_valor;
                end if;        
                                
            else
                            
                ln_saldo_ult := an_valor;
                            
            end if;


            update administracion.movimientos 
            set saldo = ln_saldo_ult 
            where id = an_id;

            update administracion.cuentas 
            set saldo = ln_saldo_ult 
            where id = an_cuentaid;
                                    
            commit;
                    
        end if;

    END;

    
END PCK_ACT_MOVIMIENTO; 

/