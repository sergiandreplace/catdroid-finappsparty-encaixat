<img src='http://p.twimg.com/AeCxby8CAAEQH98.jpg' align='right' width='250px' />
En els últims anys gran part de les entitats bancàries han estat experimentant amb la possibilitat que els seus clients puguin realitzar pagaments utilitzant només el seu telèfon mòbil en comptes dels mètodes tradicionals.

Dins del marc del FinAppsParty 2011, representants de Catdroid (la comunitat d'Android a Catalunya) han dissenyat i desenvolupat la versió inicial de la plataforma de pagaments Encaixa't.

## Com funciona Encaixa't? ##

Encaixa't disposa de dues aplicacions mòbils, una pels compradors i una pels botiguers.

En el moment que el comprador està llest per anar a la caixa i pagar la seva compra, es valida en el comerç. Aquesta validació es pot fer de diferents formes, per codi QR, per xarxes socials com ara Foursquare, Google Places, etc. Degut a la curta durada de la competició, només s'ha implementat el sistema de codis QR, pero és relativament senzill afegir noves formes d'identificar el comerç enllaçant amb xarxes socials o per geoposicionament.

Tan bon punt el comprador s'ha validat a la botiga, l'aplicació de punt de venda mostrarà els compradors que hi ha a la botiga en disposició de pagar. El venedor només ha d'identificar el comprador a qui està cobrant (a través de la fotografia del seu perfil d'usuari que es mostra al punt de venda) i carregar-li el total de la compra.

Per evitar que qualsevol pugui cobrar el que vulgui a un comprador, aquest rebrà una notificació del que se li està intentant cobrar, i haurà d'acceptar-ho activament. Un cop acceptat el pagament, la plataforma envia la informació de la transacció al servidor per fer efectiu el pagament mitjançant els mètodes de pagament establerts inicialment (aquesta part no s'ha pogut desenvolupar durant el concurs).

Aquest sistema de "handshake" (apretada de mans), terme tècnic utilitzat per referir-se a la doble validació, asegura la conformitat d'ambdues parts en la transacció i incrementa la seguretat de la plataforma en el moment del pagament.

## Qué s'ha desenvolupat? ##

En el transcurs del FinAppsParty hem desenvolupat una versió inicial de les aplicacions client, tant pel comprador com pel venedor. A més, s'ha desenvolupat el servidor que gestiona la comunicació entre ambdues parts.

Els clients s'han preparat per la plataforma Android, però la idea és que sigui agnòstic en quant a plataforma. És a dir, ara caldria realitzar les versions per altres plataformes com iPhone, BlackBerry, etc. Inclús, gràcies a la senzillesa del sistema, es podria implementar per telèfons no considerats "smartphones". També es podria adaptar a aplicacions d'escriptori, tablets, etc.

Més informació: [Com es comuniquen els components?](comunicaciocomponents.md)

## Qui som? ##

Som el Jordi Bernabeu, el Roc Boronat (sí, sí, com el carrer), el Sergi Martínez i el Jordi Varela.

Tots quatre formem part de Catdroid, una comunitat formada per desenvolupadors, usuaris i entusiastes que es mouen al voltant del sistema operatiu Android. Podeu trobar més informació a http://catdroid.org

## Altres coses que hem fet a la FinAppsParty ##

  * Hem fet molts diagrames
  * Hem fet 180 commits en el repository de Mercurial que hem utilizat pel projecte
  * Hem conegut gent molt interesant
  * Hem compartit coneixements amb altres persones
  * Hem pres molt de café
  * Hem rigut molt
  * Hem treballat molt
  * Hem fet un Mario Bros amb post-its!!! [Una foto](http://yfrog.com/z/ntiivjuj), [una altra foto encara millor](http://instagr.am/p/TxtMs/)