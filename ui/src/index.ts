import { definePlugin } from '@halo-dev/console-shared'
import HomeView from './views/HomeView.vue'
import SettingsView from './views/SettingsView.vue'
import { IconPlug } from '@halo-dev/components'
import { markRaw } from 'vue'

export default definePlugin({
  components: {},
  routes: [
    {
      parentName: 'Root',
      route: {
        path: '/example',
        name: 'Example',
        component: HomeView,
        meta: {
          title: '示例页面',
          searchable: true,
          menu: {
            name: '示例页面',
            group: '示例分组',
            icon: markRaw(IconPlug),
            priority: 0,
          },
        },
      },
    },
    {
      parentName: 'Root',
      route: {
        path: '/settings',
        name: 'Settings',
        component: SettingsView,
        meta: {
          title: '插件设置',
          searchable: true,
          menu: {
            name: '插件设置',
            group: '示例分组',
            icon: markRaw(IconPlug),
            priority: 1,
          },
        },
      },
    },
  ],
  extensionPoints: {},
})
