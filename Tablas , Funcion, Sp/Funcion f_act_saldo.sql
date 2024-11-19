CREATE OR REPLACE FUNCTION ADMINISTRACION.f_act_saldo(an_id number, an_cuentaid NUMBER, an_valor number, ac_signo char  )
 RETURN NUMBER IS
 
ln_saldo_ult NUMBER;
ln_id_max  NUMBER;

BEGIN

IF an_id > 0 and an_cuentaid > 0 and an_valor > 0 and an_valor > 0 and ( ac_signo ='+' or ac_signo = '-' ) THEN 


    ln_id_max  := 0;
    ln_saldo_ult := 0;

    select max(id)
    INTO ln_id_max  
    from administracion.movimientos m
    where m.cuenta_id = an_cuentaid
    and m.id not in (an_id ) ;

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
    where id = an_id  ;

end if;

END;
/
