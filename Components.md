# Descripció per components #

Galeria d'imatges del treball d'anàlisi (més avall):

https://www.dropbox.com/gallery/11706624/1/Encaixa%27t!%20-%20FinAppsParty?h=d3244d#gallery:0

# Client mòbil - comprador #

Aquesta aplicació Android permet a l'usuari iniciar el procés de compra en una botiga en validar-se a l'establiment (fent check-in). Per fer-ho es poden fer servir diverses opcions, des de codis QR fins al geoposicionament GPS, passant per diferents xarxes socials.

Actualment només hem implementat el registre per QR. Hem integrat la nostra aplicació amb el lector de codis de barres habitualment disponible en molts models de terminals Android. Capturant la imatge del codi amb la càmera del mòbil l'aplicació detecta en quin establiment es troba el comprador.

L'aplicació del comprador inicia la transacció anunciant la seva disponibilitat (el codi QR pot estar al costat de la caixa del comerç´, de forma que sigui fàcil i còmode). L'aplicació del punt de venda aleshores pot assignar una comanda al comprador. Un cop assignada, el comprador rep un avís per tal de confirmar la transacció.

Les comunicacions amb el servidor es fan enviant dades en format JSON en paquets comprimits amb GZIP per optimitzar la transferència.

# Client tablet - venedor #

Pot ser un mòbil, però té sentit un tablet perquè té la pantalla més gran.

L'aplicació presenta 3 llistes:
- usuaris registrats a qui es poden assignar transaccions, transaccions pendents de confirmació pel comprador, i comandes finalitzades.

Per assignar una transacció, es tria l'usuari i s'introdueix l'import total en un teclat que apareix en pantalla.

# Servidor #

Servidor desenvolupat en java executant-se sobre una instància Tomcat. La lògica implementada és la mínima que ens permet iniciar i mantenir les transaccions i enviaments de dades entre els altre dos components de la plataforma.

Més endavant es pot implementar la part de pagaments i interacció amb les bases de dades de l'entitat financera, però no hem arribat tant lluny en tan poc temps.

# Recull d'imatges #

Descripció general del funcionament de l'aplicació:

&lt;BR/&gt;


<img width='500px' align='center' src='http://rocboronat.net/xtra/finappsparty/ideaGeneral.JPG' />

Detall del procés de pagament per part del client:

&lt;BR/&gt;


<img width='500px' align='center' src='http://rocboronat.net/xtra/finappsparty/procesPagamentClient.JPG' />

Detall del procés de pagament al punt de cobrament:

&lt;BR/&gt;


<img width='500px' align='center' src='http://rocboronat.net/xtra/finappsparty/procesPuntDeCobrament.JPG' />

Esquema de les pantalles de l'aplicació del client:

&lt;BR/&gt;


<img width='500px' align='center' src='http://rocboronat.net/xtra/finappsparty/pantallesClient.JPG' />

Esquema de les pantalles del punt de cobrament.

&lt;BR/&gt;


<img width='500px' align='center' src='http://rocboronat.net/xtra/finappsparty/pantallesPuntDeCobrament.JPG' />

Altres definicions:

&lt;BR/&gt;


<img width='500px' align='center' src='http://rocboronat.net/xtra/finappsparty/definintEstandards.JPG' />