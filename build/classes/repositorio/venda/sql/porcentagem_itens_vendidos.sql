

SELECT 
    -- Primeiro campo, id_produto
     i3.id_produto
    
    -- Segundo campo, calcula porcentagem de produtos vendidos a partir das contas já efetivadas
	 ,((SUM(i3.quantidade)/(SELECT SUM(i1.quantidade) FROM itens_da_venda i1, vendas v1
	     WHERE    (i1.id_venda     =     v1.id_venda )
            AND  (v1.dt_venda    >=    '2013-01-01 00:00:00' 
	         AND   v1.dt_venda    <=    '2013-12-31 23:59:59')
	         AND  (v1.id_cliente  >=     2
	         AND   v1.id_cliente  <=     2)
	  ))*100) AS porcentagem
	  
	-- Terceiro campo, quantidade do produto vendido
	 ,SUM(i3.quantidade) AS quantidade_vendida
	 
	 -- Quarto campo, soma todos todos os produtos vendidos e da a soma
	 ,(SELECT SUM(i2.quantidade) FROM itens_da_venda i2, vendas v2
	     WHERE    (i2.id_venda     =     v2.id_venda )
            AND  (v2.dt_venda    >=    '2013-01-01 00:00:00' 
	         AND   v2.dt_venda    <=    '2013-12-31 23:59:59')
	         AND  (v2.id_cliente  >=     2
	         AND   v2.id_cliente  <=     2)
	  ) AS vendas_totais
FROM itens_da_venda i3, vendas v3 
WHERE    (i3.id_venda     =     v3.id_venda )
    AND  (v3.dt_venda    >=    '2013-01-01 00:00:00' 
	 AND   v3.dt_venda    <=    '2013-12-31 23:59:59')
	 AND  (v3.id_cliente  >=     2
	 AND   v3.id_cliente  <=     2)
	 -- Agrupa a tabela pelo id_produto, ficando assim campos com produtos que não se repetem
GROUP BY i3.id_produto 
ORDER BY porcentagem


-- ========================================================================================================

SELECT 
      -- id_produto 
    (sub.id_produto)
   
      -- quantidade total de todos os produtos vendidos 
   ,(sub.qt_total)
  
      -- porcentagem de produto vendido entre todas as vendas 
   ,(sub.qt_vendida / sub.qt_total * 100) AS porcentagem
  
      -- quantidade do produto vendida 
   ,(sub.qt_vendida)
  
      -- valor total arredadado do produto
   ,(total_vendido)
  
      -- valor médio de venda do produto 
   ,(sub.total_vendido / sub.qt_vendida) AS valor_medio_produto
FROM
    (  
    SELECT  
        i1.id_produto
        ,sum(i1.vl_unit) AS total_vendido
        ,sum(i1.quantidade) qt_vendida
        ,(  
          SELECT SUM( i2.quantidade )  
          FROM  
              itens_da_venda i2  
              INNER JOIN vendas v2  
                  ON i2.id_venda = v2.id_venda  
          WHERE  
              (v2.dt_venda BETWEEN periodo.dt_ini AND periodo.dt_fim)
			 AND
              (v2.id_cliente BETWEEN cliente.cli_ini AND cliente.cli_fin) 
         ) qt_total  
    FROM  
        itens_da_venda i1  
        INNER JOIN vendas v1  
            ON i1.id_venda = v1.id_venda  
        INNER JOIN ( SELECT '2011-03-06 00:00:00' dt_ini, '2013-03-06 00:00:00' dt_fim  ) AS periodo  
            ON v1.dt_venda BETWEEN periodo.dt_ini AND periodo.dt_fim  
        INNER JOIN ( SELECT 0 cli_ini, 9 cli_fin  ) AS cliente
            ON v1.id_cliente BETWEEN cliente.cli_ini AND cliente.cli_fin
    GROUP BY  
        i1.id_produto
    ) sub  
ORDER BY  
    porcentagem
    
-- ==================================================================================================

    -- declare l_porcentagem  decimal(14,2);  
-- declare l_quantidade_vendida int;  
 declare lvendas_totais  int;  
  
  -- Quarto campo, soma todos todos os produtos vendidos e da a soma
set lvendas_totais  = 	 
	 SELECT SUM(i2.quantidade) FROM itens_da_venda i2, vendas v2
	     WHERE    (i2.id_venda     =     v2.id_venda )
            AND  (v2.dt_venda    >=    '2012-01-01 00:00:00' 
	         AND   v2.dt_venda    <=    '2013-12-31 23:59:59')
	         AND  (v2.id_cliente  >=     0
	         AND   v2.id_cliente  <=     9999999999999999)
	 
	         
	         SELECT 
      -- id_produto 
    (sub.nome)
   
      -- quantidade total de todos os produtos vendidos 
   ,(sub.qt_total)
  
      -- porcentagem de produto vendido entre todas as vendas 
   ,(sub.qt_vendida / sub.qt_total * 100) AS porcentagem
  
      -- quantidade do produto vendida 
   ,(sub.qt_vendida)
  
      -- valor total arredadado do produto
   ,(total_vendido)
  
      -- valor médio de venda do produto 
   ,(sub.total_vendido / sub.qt_vendida) AS valor_medio_produto
FROM
    (  
    SELECT  
        p1.nome
        ,sum(i1.vl_unit) AS total_vendido
        ,sum(i1.quantidade) qt_vendida
        ,(  
          SELECT SUM( i2.quantidade )  
          FROM  
              itens_da_venda i2  
              INNER JOIN vendas v2  
                  ON i2.id_venda = v2.id_venda  
          WHERE  
              (v2.dt_venda BETWEEN periodo.dt_ini AND periodo.dt_fim)
			 AND
              (v2.id_cliente BETWEEN cliente.cli_ini AND cliente.cli_fin) 
         ) qt_total  
    FROM  
        itens_da_venda i1  
        INNER JOIN vendas v1  
            ON i1.id_venda = v1.id_venda 
        INNER JOIN produtos p1  
            ON i1.id_produto = p1.id_produto  
        INNER JOIN ( SELECT  $P{periodoInicial} dt_ini, $P{periodoFinal} dt_fim  ) AS periodo  
            ON v1.dt_venda BETWEEN periodo.dt_ini AND periodo.dt_fim  
        INNER JOIN ( SELECT $P{chaveInicialCliente} cli_ini, $P{chaveFinalCliente} cli_fin  ) AS cliente
            ON v1.id_cliente BETWEEN cliente.cli_ini AND cliente.cli_fin
    GROUP BY  
        i1.id_produto
    ) sub  
ORDER BY  
    porcentagem