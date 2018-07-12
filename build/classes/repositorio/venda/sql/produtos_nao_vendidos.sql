DROP TABLE IF EXISTS ttProdutosNaoVendidos;

CREATE TEMPORARY TABLE ttProdutosNaoVendidos AS(
    SELECT * 
    FROM Produtos p
    WHERE p.id_produto NOT IN
        (SELECT i.id_produto FROM  itens_da_venda i)
);

 SELECT 
     *
 FROM 
    ttProdutosNaoVendidos pn
 WHERE
    pn.id_produto = 5 ;   
    
 ================================================================================
 
 SELECT
     *
 FROM 
    (
     SELECT 
         *
     FROM Produtos p
     WHERE p.id_produto NOT IN
        (SELECT i.id_produto FROM  itens_da_venda i) 
	 ) AS produtosNaoVendidos 
 WHERE 
   (produtosNaoVendidos.id_produto BETWEEN 5 AND 5)
