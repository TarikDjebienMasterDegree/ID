-- Exercice 3 : Question 3 : REQUETE OLAP
-- author : SIKATRA RAKOTOBE Eric & Djebien Tarik
-- date : 10/11/2011

--i) Moyenne des ventes

-- La moyenne des ventes par année, région, catégorie:
SELECT t.annee, c.cl_r, p.category , AVG(v.qte * v.pu) as moyenne_ventes 
FROM ventes v, produits p , temps t, clients c
WHERE v.tid = t.tid 
AND v.cid = c.cl_id
AND v.pid = p.pid
GROUP BY  (t.annee, c.cl_r, p.category);

-- La moyenne des ventes par année, région :
SELECT t.annee, c.cl_r , AVG(v.qte * v.pu) as moyenne_ventes 
FROM ventes v, temps t , clients c 
WHERE v.tid = t.tid 
AND v.cid = c.cl_id 
GROUP BY  (t.annee, c.cl_r);

-- La moyenne des ventes par année pour les années 2009 et 2010:
SELECT t.annee, AVG(v.qte * v.pu) 
FROM ventes v, temps t 
WHERE v.tid = t.tid 
AND t.annee IN (2009,2010) 
GROUP BY rollup(t.annee);

--ii) La moyenne des ventes par année, selon les dimensions région et catégorie pour les années 2009 et 2010:
SELECT t.annee, c.cl_r, p.category, AVG(v.qte * v.pu) 
FROM ventes v, produits p , temps t, clients c
WHERE v.tid = t.tid 
AND v.cid = c.cl_id
AND v.pid = p.pid
AND t.annee in (2009,2010)
GROUP BY rollup(t.annee,c.cl_r, p.category);

--iii) Le produit le plus vendu par année et par catégorie (cf. RANK):
SELECT *  FROM (
  SELECT  t.annee , p.category , p.pname  , v.qte as quantiteVendue,
    rank() over (partition by annee, category order by v.qte desc) as rangProduit 
    FROM ventes v, produits p , temps t, clients c 
    WHERE v.tid = t.tid 
          AND v.cid = c.cl_id
          AND v.pid = p.pid
    GROUP BY rollup (t.annee , p.category ) , p.pname ,  v.qte 
    ORDER BY annee
)
WHERE rangProduit = 1;

--iv)

--Pour chaque année, donner le total des ventes ainsi que le total des ventes par catégorie. On ne veut pas le résultat pour le total des années (utiliser la fonction GROUPING_ID):
SELECT  t.annee,  SUM(v.qte) as total_ventes
FROM ventes v, produits p , temps t, clients c
WHERE v.tid = t.tid 
AND v.cid = c.cl_id
AND v.pid = p.pid
GROUP BY grouping sets (  rollup (t.annee) );

--Pour chaque année, donner le total des ventes par catégorie (utiliser la fonction GROUPING_ID):
SELECT  t.annee, p.category ,
grouping_id ( t.annee , p.category ) groupingid ,
 sum(v.qte) as total_ventes_par_categorie
FROM ventes v, produits p , temps t, clients c
WHERE v.tid = t.tid 
AND v.cid = c.cl_id
AND v.pid = p.pid
GROUP BY grouping sets (  rollup (t.annee, p.category) );

--v)
--Donner tous les totaux de ventes par année selon la dimension catégorie, Utilisez la commande GROUPING_SETS. :
SELECT  t.annee, p.category , SUM(v.qte) as totaux_vente
FROM ventes v, produits p , temps t, clients c
WHERE v.tid = t.tid 
AND v.cid = c.cl_id
AND v.pid = p.pid
GROUP BY grouping sets  (rollup (t.annee,p.category));

--Donner tous les totaux de ventes selon la dimension cl_name (nom du client). Utilisez la commande GROUPING_SETS. :

SELECT  c.cl_name , SUM(v.qte)  AS totaux_vente
FROM ventes v, produits p , temps t, clients c
WHERE v.tid = t.tid 
AND v.cid = c.cl_id
AND v.pid = p.pid
GROUP BY grouping sets  (rollup (c.cl_name ));

--vi) Quel est le meilleur mois de vente du produit "siropDerable" pour chacune des années ?
SELECT * FROM (
SELECT t.annee,  t.mois ,
RANK() OVER (PARTITION BY t.annee ORDER BY SUM(v.qte) DESC) AS quantite_rang 
FROM ventes v, produits p , temps t
WHERE v.tid = t.tid 
AND v.pid = p.pid
AND p.pname = 'siropDerable'
GROUP BY rollup( t.mois  , t.annee ) 
)
WHERE  quantite_rang  = 1;

--vii) Quelle est la quantité de produits vendus pour chaque catégorie, les 5 premiers jours de chaque mois de 2010 ? (NTILE)
SELECT * FROM 
(SELECT DISTINCT t.mois, p.category ,  SUM(v.qte) as quantite_vendue ,
ntile(5) over (order by  t.jour) as cinqpremierjour
FROM ventes v, produits p , temps t, clients c
WHERE v.tid = t.tid 
AND v.cid = c.cl_id
AND v.pid = p.pid
AND t.annee=2010
GROUP BY rollup ( p.category , t.mois ) , t.jour 
ORDER BY p.category,t.mois
)
WHERE cinqpremierjour = 1;

--viii) Quelle est la répartition par tiers des catégories selon leurs quantités totales vendues en 2010 ?
SELECT p.category, 
            sum(v.qte) as quantite_vendue ,
            ntile(3) over (order by  sum(v.qte)) as repartitioncategory
FROM ventes v, produits p , temps t, clients c
WHERE v.tid = t.tid 
AND v.cid = c.cl_id
AND v.pid = p.pid
AND t.annee=2010
GROUP BY  p.category;
