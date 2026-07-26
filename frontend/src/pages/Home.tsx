import Badge from '@components/Badge'
import { useDocumentTitle } from '@hooks/useDocumentTitle'
import { APP_NAME, APP_TAGLINE } from '@lib/config'

export default function Home() {
  useDocumentTitle(APP_NAME)

  return (
    <main className="flex min-h-screen flex-col items-center justify-center gap-4 bg-brand-50 p-8">
      <Badge>Scaffold ready</Badge>
      <h1 className="text-4xl font-bold text-brand-900">{APP_NAME}</h1>
      <p className="max-w-md text-center text-slate-600">{APP_TAGLINE}</p>
    </main>
  )
}
