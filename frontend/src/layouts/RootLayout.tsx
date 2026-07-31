import type { ReactNode } from 'react'

type RootLayoutProps = {
  children: ReactNode
}

/** Base page shell. */
export default function RootLayout({ children }: RootLayoutProps) {
  return (
    <div className="flex min-h-screen flex-col bg-brand-50">
      <main className="flex flex-1 flex-col items-center justify-center gap-4 p-8">{children}</main>
    </div>
  )
}
