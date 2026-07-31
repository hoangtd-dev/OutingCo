# OutingCo — Frontend

React + TypeScript + Vite + Tailwind CSS scaffold for the OutingCo outing-coordination platform.

## Getting started

```bash
npm install
npm run dev
```

The dev server runs on http://localhost:5173.

## Scripts

| Script                 | Purpose                                     |
| ---------------------- | ------------------------------------------- |
| `npm run dev`          | Start the Vite dev server with HMR          |
| `npm run build`        | Type-check the project and build to `dist/` |
| `npm run preview`      | Serve the production build locally          |
| `npm run lint`         | Run ESLint over the project                 |
| `npm run lint:fix`     | Run ESLint with `--fix`                     |
| `npm run format`       | Format the project with Prettier            |
| `npm run format:check` | Verify formatting without writing           |
| `npm run typecheck`    | Type-check without emitting                 |

## Folder structure

```
src/
├── app/          App shell — providers, routing, global wiring
├── assets/       Static assets imported by components
├── components/   Shared, presentational UI primitives
├── features/     One folder per domain module (auth, outings, bookings, …)
│   └── home/
│       └── pages/    Route-level screens owned by that feature
├── hooks/        Reusable React hooks
├── layouts/      Reusable page shells composed by pages
├── lib/          Framework-agnostic helpers, config, API client
└── types/        Shared TypeScript types
```

Pages live inside the feature that owns them, so a feature ships its screens, components, and
hooks together. Anything shared across features moves up into `components/`, `hooks/` or `lib/`.
Page framing (header, sidebar, spacing) belongs in `layouts/` so screens don't repeat it.

## Path aliases

Imports resolve through aliases declared in both `tsconfig.app.json` and `vite.config.ts`:

`@/*`, `@app/*`, `@components/*`, `@features/*`, `@hooks/*`, `@layouts/*`, `@lib/*`, `@types/*`

## Environment variables

Copy `.env.example` to `.env.local` and adjust. Only variables prefixed with `VITE_` are exposed
to the client.

## Conventions

- Prettier owns formatting (no semicolons, single quotes, 100-column width); `eslint-config-prettier`
  disables any ESLint rule that would conflict.
- Tailwind is configured through the `@tailwindcss/vite` plugin; theme tokens live in the `@theme`
  block in `src/index.css`.
