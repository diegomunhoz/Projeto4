SELECT 
    c.* 
FROM 
    compras c, formas_de_pagamentos fp
WHERE
	(c.id_compra = fp.id_compra)
	 AND
   (fp.status_pagamento LIKE 'PENDENTE')
GROUP BY 
    c.id_compra
    
SELECT DISTINCT 
    c.* 
FROM 
    compras c
    JOIN formas_de_pagamentos fp
        ON (
		      (c.id_compra = fp.id_compra) 
			    AND 
			   (fp.status_pagamento LIKE 'PENDENTE')
			  )    