# LOG200 – Laboratoire 1 : Tic-Tac-Toe

Joueur CPU pour le Tic-Tac-Toe utilisant les algorithmes **MinMax** et **Alpha-Beta**.

## Structure

| Fichier | Rôle |
|---|---|
| `src/Board.java` | Plateau de jeu (3x3), coups possibles, évaluation |
| `src/CPUPlayer.java` | Joueur CPU (MinMax et Alpha-Beta) |
| `src/Move.java` | Un coup (ligne, colonne) |
| `src/Mark.java` | Valeurs d'une case : `X`, `O`, `EMPTY` |

> Ne pas modifier la signature des méthodes existantes ni le nom des classes.

## Compilation

```bash
javac -d out src/*.java
```

## Politique des messages de commit

Format : `<type>: <description courte>`

- Écrit en français, à l'impératif, sans point final (ex. `feat: ajoute l'algorithme MinMax`)
- 72 caractères maximum pour la première ligne
- Un commit = un changement logique

Types :

- `feat` : nouvelle fonctionnalité
- `fix` : correction de bogue
- `refactor` : restructuration sans changement de comportement
- `test` : ajout ou modification de tests
- `docs` : documentation
- `chore` : configuration, fichiers divers
