# Leboncoin Android Test


## Architecture et patterns

MVVM avec clean Architecture multi-modulaire : Le choix d'architecture MVVM clean Architecture permet de découpler les différente couche de l'application (presentation, domaine, data). ça permet
de séparer en modules et packages le projet pour plus de lisibilité et faciliter de navigation structuré.

Cette architecture favorise la facilité de maintenabilité ,  évolutivité et testabilité du code. Ainsi les changements sont plus souples a mettre en oeuvres et les risques de regression diminuent.

ça applique aussi les principes SOLID et donc assure un processus de développement rigoureux et encadré.

## Librairies
Le projet intègre principalement des libraire maintenu par google. Ceci dans un cadre d'alignement au recommendation de la communauté android puisque ses librairies s'intègrent d'une façon sécurisé et transparente. Aussi les outils disponibles via ces librairies permettent de profiter des outils accessible pour bien respecter les nécessités de compatibilité avec les composant Android

ex: lifecycle, couroutines , etc...

Pour l'injection des dependences j'ai utilisé Hilt parcequ'il reduit le code boilerplate ,simplifie la configuration, découple les dependences du build et utilise des composants standard

Pour le cache j'ai utilisé room: facilité la gestion de la database sqlite, plus facile et structure a mettre en place  et s'intègre parfaitement avec les composant android et les courotines

Pour la couche networking j'ai utilisé retrofit avec gson pour la désarialisation. ce sont les libs les plus utilisé dans les projets android grace a leur facitlié d'intégration, le code boilerplate réduit et la compatibilité avec les differents autres outils et composants

Et finalement pour le téléchargement des images j'ai utilisé Picasso pour la facitliter d'intégration et les performances de téléchargement couplé a un systéme de cache et lazy loading permettant ainsi de paralleliser cette tache lourde de téléchargement des images. J'ai du utiliser glide mais l'integration d'un client okhttp custom n'est pas aussi transparent afin de modifier les headers et ajouter un user agent

