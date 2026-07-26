import { useEffect } from 'react'

import { APP_NAME } from '@lib/config'

/** Sets document.title, suffixed with the app name for every page but the root. */
export function useDocumentTitle(title?: string) {
  useEffect(() => {
    document.title = title && title !== APP_NAME ? `${title} · ${APP_NAME}` : APP_NAME
  }, [title])
}
