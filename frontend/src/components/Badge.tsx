import type { ReactNode } from 'react'

type BadgeProps = {
  children: ReactNode
}

export default function Badge({ children }: BadgeProps) {
  return (
    <span className="rounded-full bg-brand-500 px-3 py-1 text-sm font-medium tracking-wide text-white uppercase">
      {children}
    </span>
  )
}
