<template>
  <div>
    <h2>插件设置</h2>
    <div>
      <label for="position">显示位置:</label>
      <select id="position" v-model="position">
        <option value="bottom-right">右下角</option>
        <option value="bottom-left">左下角</option>
      </select>
    </div>
    <div>
      <label for="showControls">显示控制横跳按钮:</label>
      <input type="checkbox" id="showControls" v-model="showControls">
    </div>
    <div>
      <label for="controlButtonText">控制横跳按钮内容:</label>
      <input type="text" id="controlButtonText" v-model="controlButtonText">
    </div>
    <div>
      <label for="idleSway">不动时晃动幅度:</label>
      <input type="number" id="idleSway" v-model="idleSway">
    </div>
    <button @click="saveSettings">保存设置</button>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { usePluginConfig } from '@halo-dev/console-shared';

const { config, updateConfig } = usePluginConfig('SakanaWidget');
const position = ref(config.value?.position || 'bottom-right');
const showControls = ref(config.value?.showControls || true);
const controlButtonText = ref(config.value?.controlButtonText || '横跳');
const idleSway = ref(config.value?.idleSway || 10);

const saveSettings = async () => {
  await updateConfig({
    ...config.value,
    position: position.value,
    showControls: showControls.value,
    controlButtonText: controlButtonText.value,
    idleSway: idleSway.value,
  });
};
</script>

<style scoped>
/* 样式 */
</style>
